-- TODO Add a foreign key column to employee-table refrencing company-table
ALTER TABLE EMPLOYEE
    ADD CONSTRAINT COMPANY_FK FOREIGN KEY (ID) REFERENCES COMPANY(ID);