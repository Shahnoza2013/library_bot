package uz.pdp.backend.model.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.backend.enums.UserRole;
import uz.pdp.frondend.bot.states.base.BaseState;
import uz.pdp.frondend.bot.states.child.MainStates;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users  {
    private Long id;
    private String userName;
    private String firstName;
    private String phoneNumber;
    private String password;
    private String baseState;
    private String state;
    private UserRole role;

}
