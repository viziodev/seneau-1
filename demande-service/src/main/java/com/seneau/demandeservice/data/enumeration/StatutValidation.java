package com.seneau.demandeservice.data.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatutValidation {
    VALIDATED (0),
    REJECTED (1);
    private final long statutIndex;
}
