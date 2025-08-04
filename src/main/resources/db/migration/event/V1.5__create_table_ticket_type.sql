CREATE TABLE ticket_type (
                        id SERIAL  PRIMARY KEY,
                        event_id integer      not null
                            references public.event,
                        name               varchar(100)   not null,
                        price              numeric(10, 2) not null,
                        total_quantity     integer        not null,
                        sale_quantity integer        not null,
                        active BOOLEAN,
                        created_at   timestamp default CURRENT_TIMESTAMP

);