package com.xoj.backend.entity;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

public class Judge implements Serializable {

    @Id
    private UUID token;

    private int status;

    private float memory_cost;

    private float time_cost;
}
