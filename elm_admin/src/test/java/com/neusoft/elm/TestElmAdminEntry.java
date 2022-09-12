package com.neusoft.elm;

import org.junit.Before;
import org.junit.Test;

public class TestElmAdminEntry {
    private ElmAdminEntry elmAdminEntry;

    @Before
    public void init() {
        elmAdminEntry = new ElmAdminEntry();
    }

    @Test
    public void testWork() {
        elmAdminEntry.work();
    }
}
