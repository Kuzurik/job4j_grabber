package ru.job4j.test;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MainTest {

    @Test
    public void test() {
        Main main = new Main();
        assertThat(main.sum(2, 2), is(4));
    }

}