package info.morgia.timetrails.core.interfaces;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DemoHelloRequest {
    private String message;
    private Date timestamp;
}
