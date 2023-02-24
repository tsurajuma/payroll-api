package com.umasuraj.payroll.api.payload;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author umasuraj
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String designation;

    private LocalDateTime addTimestamp;

    private LocalDateTime updateTimestamp;
}
// end class UserDto{}
