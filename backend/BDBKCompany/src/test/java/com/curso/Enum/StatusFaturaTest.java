package com.curso.Enum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StatusFaturaTest {

    @Test
    public void toEnum_null_returnsNull() {
        assertNull(StatusFatura.toEnum(null));
    }

    @Test
    public void toEnum_valid_returnsEnum() {
        StatusFatura s = StatusFatura.toEnum(0);
        assertNotNull(s);
        assertEquals(StatusFatura.ABERTA, s);
        assertEquals(Integer.valueOf(0), s.getId());
        assertEquals("ABERTA", s.getDescricao());
    }

    @Test
    public void toEnum_invalid_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> StatusFatura.toEnum(99));
    }
}
