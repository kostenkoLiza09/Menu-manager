package com.example.menumanager.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class IdServiceTest {

    private final IdService idService = new IdService();
    @Test
    void createId() {

        String id1= idService.createId();
        String id2 = idService.createId();

        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);

    }
}