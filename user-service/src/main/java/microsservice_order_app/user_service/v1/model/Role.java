package microsservice_order_app.user_service.v1.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "roles")
@Entity(name = "Role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String name;
}
