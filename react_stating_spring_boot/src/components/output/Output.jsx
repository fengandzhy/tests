import React, {Component} from 'react';
import PubSub from 'pubsub-js'
import 'bootstrap/dist/css/bootstrap.min.css'
export default class Output extends Component {

    state = {
        username: '',
        moneyWords: ''
    };
    componentDidMount() {
        this.token = PubSub.subscribe('transfer-result', (name,data) =>{
            this.setState(data);
        });
    }
    componentWillUnmount() {
        PubSub.unsubscribe(this.token);
    }
    render() {
        return (
            <div>
                <div>{this.state.username}</div>
                <div>{this.state.moneyWords}</div>
            </div>
        );
    }
}