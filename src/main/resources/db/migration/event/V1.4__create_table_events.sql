CREATE TABLE event (
                        id SERIAL  PRIMARY KEY,
                        users_id integer      not null
                            references public.users,
                        title        varchar(200) not null,
                        description  text,
                        image_url    BYTEA,
                        cep VARCHAR(100) ,
                        road VARCHAR(255) ,
                        complement VARCHAR(400) ,
                        neighborhood VARCHAR(100) ,
                        locality VARCHAR(100) ,
                        state VARCHAR(100) ,
                        number_locality varchar(50),
                        start_date   timestamp    not null,
                        end_date   timestamp    not null,
                        created_at   timestamp default CURRENT_TIMESTAMP,
                        status VARCHAR(50),
                        active BOOLEAN
);