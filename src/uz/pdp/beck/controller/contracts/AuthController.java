package uz.pdp.beck.controller.contracts;

import uz.pdp.beck.payload.SignInDTO;
import uz.pdp.beck.payload.SignUpDTO;
import uz.pdp.beck.payload.UserDTO;

public interface AuthController {
    UserDTO signUp(SignUpDTO dto);

    UserDTO signIn(SignInDTO signInDTO);
}
