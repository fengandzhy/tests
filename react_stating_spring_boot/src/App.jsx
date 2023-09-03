import React from 'react';
import Input from "./components/input/Input";
import Output from "./components/output/Output";
import 'bootstrap/dist/css/bootstrap.min.css'

class App extends React.Component {
    render() {
        return (
            <div className="container">
                <Input></Input>
                <Output></Output>
            </div>
        );
    }
}

export default App;