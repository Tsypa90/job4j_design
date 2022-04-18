package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("username"),is("postgres"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithCommentAndEmptyLines() {
        String path = "./data/pair_with_comment_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("username"),is("postgres"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithIllegalArgument() {
        String path = "./data/pair_with_illegal_argument.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("username"),is("postgres"));
    }
}