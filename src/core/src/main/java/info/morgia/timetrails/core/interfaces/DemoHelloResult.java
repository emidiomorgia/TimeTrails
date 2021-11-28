package info.morgia.timetrails.core.interfaces;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class DemoHelloResult {
    private String message;
    private Date timestamp;
}
