package request;

import java.util.List;

import com.example.demo.models.entity.Phone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    private Long id;

    private String name;
	
	private String email;
	
	private String password;
	
	private List<Phone> phones;
    
}// Class Closure