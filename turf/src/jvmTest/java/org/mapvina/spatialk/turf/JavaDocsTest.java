package com.mapvina.spatialk.turf;

import static com.mapvina.spatialk.turf.measurement.Measurement.offset;
import static com.mapvina.spatialk.units.International.Kilometers;

import org.junit.Test;
import com.mapvina.spatialk.geojson.Position;

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
