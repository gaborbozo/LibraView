package hu.bozgab.cinematic.shared;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@AllArgsConstructor
public class CacheEntry<V> {

    @Getter
    private final V value;

    private final long initializedAt = System.currentTimeMillis();

    private final long expirationTime;

    public boolean isExpired() {
        return System.currentTimeMillis() - initializedAt > expirationTime;
    }

}
