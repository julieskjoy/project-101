-- TODO Create a table for Employee containing an ID, firstname, lastname and date of birth
CREATE TABLE EMPLOYEE (
    ID              NUMBER(19)      GENERATED BY DEFAULT AS IDENTITY,
    FIRST_NAME      VARCHAR(20)     NOT NULL,
    LAST_NAME       VARCHAR(30)     NOT NULL,
    DATE_OF_BIRTH   DATE,
    CONSTRAINT EMPLOYEE_PK PRIMARY KEY (ID)
);