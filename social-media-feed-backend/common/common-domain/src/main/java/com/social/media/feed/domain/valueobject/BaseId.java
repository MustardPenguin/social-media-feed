package com.social.media.feed.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class BaseId {

    private final UUID value;

    protected BaseId(UUID id) {
        this.value = id;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseId baseId = (BaseId) o;
        return Objects.equals(value, baseId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
