import * as React from "react";

class EmployeeListPage extends React.Component {

    apiReadAllEmployees = async () => {
        const response = get("/employees").then(r => {
            console.log(r);
        });
        console.log(response);
    }

    render(){
        return <h1>EmployeeListPage</h1>;
    }
}

export {EmployeeListPage};
