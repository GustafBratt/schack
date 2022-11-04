package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.Drag;

import java.util.List;

public interface PjasIf {
    char CONST_BONDE = 'B';
    char CONST_KUNG = 'K';

    List<Drag> mojligaDrag();
}
