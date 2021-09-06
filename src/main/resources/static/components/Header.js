import html from "../js/htm.js";
import {
    AppBar,
    Box,
    Button,
    Divider,
    IconButton,
    Link,
    Menu,
    MenuItem,
    Toolbar,
    Typography
} from "../js/material-ui.js";
import MaterialIcon from "./MaterialIcon.js";
import {useContext, useState} from "../js/react.js";
import Logout from "./Logout.js";
import GantiPassword from "./GantiPassword.js";
import {AuthContext} from "../App.js";

function Header(props) {

    const {user, setUser} = useContext(AuthContext);
    const [anchorEl, setAnchorEl] = useState(null);

    const handleMenu = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };
    const [openGantiPassword, setOpenGantiPassword] = useState(false);
    const [openLogout, setOpenLogout] = useState(false);

    return html`
            <${AppBar} position="static" sx=${{ background: "white", color:"#00c853"}}>
                <${Toolbar}>
                    <${IconButton}
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx=${{ mr: 2 }}
                        onClick=${props.toggleDrawer}
                    >
                        <${MaterialIcon}>menu<//>
                    <//>
                    <${Typography} variant="h6" component="div" sx=${{ flexGrow: 1, color:"#4c8c4a"}}>
                        <span style=${{color:"#009624"}}>G-Four</span>Leave
                    <//>
                    ${user ? html`
                        <${Typography} variant="h6" component="div" sx=${{ color:"#009624"}}>
                            <span style=${{color:"#4c8c4a"}}>Halo </span>${user.namaLengkap}
                        <//>
                    ` : null}
                    <div>
                        <${IconButton}
                                size="large"
                                aria-label="account of current user"
                                aria-controls="menu-appbar"
                                aria-haspopup="true"
                                onClick=${handleMenu}
                                color="inherit"
                        >
                            <${MaterialIcon}>account_circle<//>
                        <//>
                        <${Menu}
                                    id="menu-appbar"
                                    anchorEl=${anchorEl}
                                    anchorOrigin=${{
                                    vertical: 'top',
                            horizontal: 'right',
                            }}
                            keepMounted
                            transformOrigin=${{
                            vertical: 'top',
                            horizontal: 'right',
                            }}
                            open=${Boolean(anchorEl)}
                            onClose=${handleClose}
                            >
                            <${MenuItem} onClick=${handleClose}><${MaterialIcon}>person<//> Profil Saya<//>
                            <${MenuItem} onClick=${() => setOpenGantiPassword(true)} component=${Link} to="/gantipasword"><${MaterialIcon} >password<//> Ganti Password<//>
                            <${GantiPassword} open=${openGantiPassword} onClose=${() => setOpenGantiPassword()}/>
                            <${Divider}/>
                            <${MenuItem} onClick=${() => {setOpenLogout(true); handleClose();}} component=${Link} to="/logout"><${MaterialIcon}>logout<//> Keluar<//>
                            <${Logout} open=${openLogout} setOpen=${setOpenLogout}/>
                        <//>
                    </div>
                    
                <//>
            <//>
    `;
}

export default Header;