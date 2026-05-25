package io.github.mapvina.spatialk.turf;

import static io.github.mapvina.spatialk.turf.measurement.Measurement.offset;
import static io.github.mapvina.spatialk.units.International.Kilometers;

import org.junit.Test;
import io.github.mapvina.spatialk.geojson.Position;

public class JavaDocsTest {
  @Test
  public void example() {
    // --8<-- [start:example]
    Position origin = new Position(-75.0, 45.0);
    Position dest = offset(origin, 100, Kilometers, 0);
    dest.getLongitude();
    dest.getLatitude();
    // --8<-- [end:example]
  }
}
