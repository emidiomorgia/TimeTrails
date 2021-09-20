package info.morgia.timetrails.auth.interfaces;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDetailResult {
    private int userId;
    private String username;
}
