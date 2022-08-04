package sec01.ex01;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberDTO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	
	
	
} //end class
