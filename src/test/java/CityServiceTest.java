import dtos.CityInfoDTO;
import org.junit.Test;
import services.CityService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CityServiceTest {
    @Test
    public void testGetCityInfo() throws Exception {
        CityService service = new CityService();
        CityInfoDTO city = service.getCityInfo("Roskilde");

        assertNotNull(city);
        assertEquals("Roskilde", city.getCityName());
    }
}