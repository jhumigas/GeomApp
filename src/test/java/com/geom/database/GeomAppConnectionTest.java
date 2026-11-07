package com.geom.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GeomAppConnectionTest {

	@Test
	void systemPropertyWinsOverEnvironment() {
		assertEquals("/tmp/a.db", GeomAppConnection.resolveDbPath("/tmp/a.db", "/tmp/b.db"));
	}

	@Test
	void environmentIsUsedWhenNoProperty() {
		assertEquals("/tmp/b.db", GeomAppConnection.resolveDbPath(null, "/tmp/b.db"));
		assertEquals("/tmp/b.db", GeomAppConnection.resolveDbPath("  ", "/tmp/b.db"));
	}

	@Test
	void defaultsToTheFileInTheWorkingDirectory() {
		assertEquals("GeomAppDatabase.db", GeomAppConnection.resolveDbPath(null, null));
		assertEquals("GeomAppDatabase.db", GeomAppConnection.resolveDbPath("", ""));
	}
}
