--liquibase formatted sql

--changeset app:1
create table if not exists users (
    id uuid primary key,
    username varchar(255) not null unique
);
create table if not exists service_providers (
    id uuid primary key,
    name varchar(255) not null unique,
    owner_id UUID not null,
    constraint fk_service_provider_owner foreign key(owner_id) references users(id)
);
create table if not exists appointments (
    id uuid primary key,
    start_time timestamp with time zone not null,
    end_time timestamp with time zone not null,
    customer_id uuid not null,
    service_provider_id uuid not null,
    status varchar(64) not null,
    constraint fk_appointment_customer foreign key(customer_id) references users(id),
    constraint fk_appointment_service_provider foreign key(service_provider_id) references service_providers(id)
);
create table if not exists service_providers_appointments (
    service_provider_id uuid not null,
    appointments_id uuid not null,
    constraint fk_service_providers_appointments foreign key(service_provider_id) references service_providers(id),
    constraint fk_appointments_service_provider foreign key(appointments_id) references appointments(id)
);
create table if not exists users_appointments (
    user_id uuid not null,
    appointments_id uuid not null,
    constraint fk_users_appointments foreign key (user_id) references users(id),
    constraint fk_appointments_users foreign key (appointments_id) references appointments(id)
);
create table if not exists notifications (
    id uuid primary key,
    timestamp timestamp with time zone not null,
    message text,
    recipient_id uuid not null,
    read boolean default false not null,
    constraint fk_notification_recipient foreign key(recipient_id) references users(id)
);