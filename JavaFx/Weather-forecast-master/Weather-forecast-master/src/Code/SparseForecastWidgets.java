package Code;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SparseForecastWidgets {
    private List<ForecastMapping> sparseWidgets;

    public SparseForecastWidgets() {
        sparseWidgets = new ArrayList<>();
        setSparseWidgets();
    }
    public List<ForecastMapping> getSparseWidgets() {
        return sparseWidgets;
    }
    public void setSparseWidgets() {
        SparseWeatherForecast sparseWeatherForecast= new SparseWeatherForecast();
        Set<String> keys = sparseWeatherForecast.getSparseForecastKeys();
        int count = 0;
        for(String key : keys) {
            if(count++ > 4)
                break;
           SparseWeather sparseWeather = sparseWeatherForecast.getSparseForecast(key);
           SparseForecastWidget sparseForecastWidget = new SparseForecastWidget(sparseWeather.getDayOfMonth(), sparseWeather.getMinTemperature(),
                    sparseWeather.getMaxTemperature(), sparseWeather.getWeatherIcon(), sparseWeather.getDayOfWeek());
            sparseWidgets.add(new ForecastMapping(sparseForecastWidget.getSparseForecastWidget(), key));
        }
    }
}
