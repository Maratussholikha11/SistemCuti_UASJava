import html from "../js/htm.js";
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "../js/material-ui.js";
import {useContext} from "../js/react.js";
import {AuthContext} from "../App.js";
import {useHistory} from "../js/react-router-dom.js";

function Logout(props){

    const handleClose = () => {
        props.setOpen(false);
    };

    const {user, setUser} = useContext(AuthContext);
    const history = useHistory();

    const logout = () => {
        setUser(null);
        localStorage.removeItem('token');
        props.setOpen(false);
        history.push('/login');
    };

    return html`
        <${Dialog }
                open=${props.open}
                onClose=${props.onClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
        >
            <${DialogTitle} id="alert-dialog-title">
                ${"Keluar"}
            <//>
            <${DialogContent}>
                <${DialogContentText} id="alert-dialog-description">
                    Apakah anda yakin keluar dari akun ini?
                <//>
            <//>
            <${DialogActions}>
                <${Button} onClick=${logout}>Ya<//>
                <${Button} onClick=${handleClose} autoFocus>
                    Tidak
                <//>
            <//>
        <//>
    `;
}
export  default  Logout;