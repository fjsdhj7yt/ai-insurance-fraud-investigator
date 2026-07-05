CREATE TABLE roles (
                       id UUID PRIMARY KEY,
                       name VARCHAR(50) NOT NULL UNIQUE,
                       description VARCHAR(255),
                        created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL
);

CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       first_name VARCHAR(100) NOT NULL,
                       last_name VARCHAR(100) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       status VARCHAR(20) NOT NULL,
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL
);

CREATE TABLE user_roles (
                            user_id UUID NOT NULL,
                            role_id UUID NOT NULL,

                            PRIMARY KEY (user_id, role_id),

                            CONSTRAINT fk_user_roles_user
                                FOREIGN KEY (user_id)
                                    REFERENCES users(id)
                                    ON DELETE CASCADE,

                            CONSTRAINT fk_user_roles_role
                                FOREIGN KEY (role_id)
                                    REFERENCES roles(id)
                                    ON DELETE CASCADE
);