package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    public void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isEqualTo("Sphere")
                .isNotNull();
    }

    @Test
    public void whenEdgeZeroAndTypeUnknown() {
        Box box = new Box(0, 0);
        String name = box.whatsThis();
        assertThat(name)
                .isEqualTo("Unknown object")
                .isNotEmpty();
    }

    @Test
    public void whenVertexTenAndWhatsThis() {
        Box box = new Box(10, 5);
        String name = box.whatsThis();
        assertThat(name)
                .isEqualTo("Unknown object")
                .containsAnyOf("o");
    }

    @Test
    public void whenEdgeMinusSixAndIsExistFalse() {
        Box box = new Box(5, -6);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse()
                .isNotNull();
    }

    @Test
    public void whenVertexFourAndIsExistTrue() {
        Box box = new Box(4, 5);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue()
                .isNotNull();
    }

    @Test
    public void whenVertexAndEdgeZeroGetAreaZero() {
        Box box = new Box(0, 0);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(0d)
                .isNotNull();
    }

    @Test
    public void whenVertexFourAndTwoFourGetArea() {
        Box box = new Box(4, 2);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(6.9d, withPrecision(0.06d))
                .isCloseTo(6.9, withPrecision(0.1d));
    }

    @Test
    public void whenVertexThreeEdgeFiveAndGetNumberOfVertices() {
        Box box = new Box(3, 5);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(-1)
                .isLessThan(0);
    }

    @Test
    public void whenVertexEightAndEdgeTwoAndGetNumberOfVertices() {
        Box box = new Box(8, 2);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(8)
                .isBetween(7, 9);
    }
}