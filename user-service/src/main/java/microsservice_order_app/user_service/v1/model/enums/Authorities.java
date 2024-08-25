package microsservice_order_app.user_service.v1.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authorities {
    ADMIN(1L),
    USER(2L);

    long roleId;
}
