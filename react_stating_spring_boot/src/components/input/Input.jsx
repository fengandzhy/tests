import React, {Component} from 'react';
import PubSub from 'pubsub-js'
import 'bootstrap/dist/css/bootstrap.min.css'
import axios from "axios";

export default class Input extends Component {

    getResponse = () =>{
        const {username, money} = this;
        if(username.value.trim() === '' || money.value.trim() === ''){
            alert('username or money cannot be empty');
            return;
        }
        const isNum = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
        if(!isNum.test(money.value)) {
            alert('money is not correct!');
            return;
        }
        axios.post('http://localhost:8080/test/convert', {
            username: username.value,
            money: money.value
        }).then((response) => {
            const {data} = response.data;
            PubSub.publish('transfer-result', data);
        });
    }
    render() {
        return (
            <div>
                <form className="form-horizontal" id="form_Company" method="post" style={{margin: '5px'}}>
                    <div className="form-group row">
                        <label className="col-md-3 col-sm-3 text-right" htmlFor="username"><span
                            className="text-danger">*</span>username：</label>
                        <div className="col-md-9 col-sm-9">
                            <input ref ={c => {this.username = c}} className="form-control" type="text" id="username" name="username"
                                   placeholder="username" required="required"/>
                        </div>
                    </div>
                    <div className="form-group row">
                        <label className="col-md-3 col-sm-3 text-right" htmlFor="money"><span
                            className="text-danger">*</span>money：</label>
                        <div className="col-md-9 col-sm-9">
                            <input ref ={c => {this.money = c}}  className="form-control" type="text" id="money" name="money" placeholder="money"
                                   required="required"/>
                        </div>
                    </div>
                    <div className="form-group row">
                        <div className="col-xs-6 col-sm-6 text-right">
                            <button type="reset" className="btn btn-primary" >Reset</button>
                        </div>
                        <div className="col-xs-6 col-sm-6 text-left">
                            <button id="btn_Save" type="button" className="btn btn-success" onClick={this.getResponse}>Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        );
    }
}
