package com.seneau.agentservice.data.model;

import java.util.List;


public record Application(String name, List<String> roles) {
}
