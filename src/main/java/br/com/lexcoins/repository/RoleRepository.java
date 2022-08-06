package br.com.lexcoins.repository;

import br.com.lexcoins.enums.RoleName;
import br.com.lexcoins.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {

    RoleModel findByRoleName(RoleName roleName);
}
