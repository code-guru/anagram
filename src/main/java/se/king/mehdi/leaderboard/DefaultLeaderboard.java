package se.king.mehdi.leaderboard;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DefaultLeaderboard implements Leaderboard {
    private final Map<String, Entry> entries;
    private NavigableSet<Entry> standings;

    DefaultLeaderboard() {
        entries = new ConcurrentHashMap<>();
        standings = new ConcurrentSkipListSet<>((o1, o2) -> {
            int compare = o2.getScore() - o1.getScore();
            if (Objects.equals(o1.getUid(), o2.getUid())) return 0;
            else if (compare == 0) return 1;
            return compare;
        });

    }

    public void submitScore(String uid, int score) {
        Entry entry = new Entry(uid, score);
        standings.remove(entry);
        standings.add(entry);
        entries.put(uid, entry);
        Set<Entry> tailSet = standings.tailSet(entry, true);
        Entry lower = standings.lower(entry);
        int entryPosition = lower == null ? 1 : lower.getPosition() + 1;
        AtomicInteger position = new AtomicInteger(entryPosition);
        tailSet.forEach(e -> e.setPosition(position.getAndIncrement()));

    }

    public List<Entry> getLeaderBoard(String uid) {
        Entry entry = entries.get(uid);
        if (entry == null) {
            return Collections.emptyList();
        }
        int position = entry.getPosition();
        int offset = 0;
        if (position > 3) {
            offset = position - 3;
        }
        int limit = offset + position + 2;
        return standings.stream()
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }
}
