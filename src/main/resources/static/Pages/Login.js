import html from "../js/htm.js";
import {
    Box, Button,
    Container, Divider,
    FormControl, IconButton, InputAdornment,
    InputLabel,
    OutlinedInput,
    Paper,
    Stack,
    TextField,
    Typography
} from "../js/material-ui.js";
import {useContext, useState} from "../js/react.js";
import MaterialIcon from "../components/MaterialIcon.js";
import {AuthContext, getUser} from "../App.js";
import {Redirect, useHistory} from "../js/react-router-dom.js";

function Login (){

    const [values, setValues] = useState({
        amount: '',
        password: '',
        weight: '',
        weightRange: '',
        showPassword: false,
    });

    const {user, setUser} = useContext(AuthContext);
    const [usernameError, setUsernameError] = useState(null);
    const [passwordError, setPasswordError] = useState(null);
    const history = useHistory();

    const handleChange = (prop) => (event) => {
        setValues({ ...values, [prop]: event.target.value });
    };

    const handleClickShowPassword = () => {
        setValues({
            ...values,
            showPassword: !values.showPassword,
        });
    };

    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

    const login = async (event) => {
        event.preventDefault();
        const response = await fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: event.target.username.value,
                password: event.target.password.value,
            }),
        });
        if (response.ok) {
            const token = await response.text();
            localStorage.setItem('token', token);
            const user = await getUser();
            setUser(user);
            console.log(user);
            if (user.role === 1) {
                history.push('/dashboardkaryawan');
            } else {
                history.push('/');
            }
        } else {
            const errorMessage = await response.text();
            if (response.status === 404) {
                setPasswordError(null);
                setUsernameError(errorMessage);
            } else {
                setUsernameError(null);
                setPasswordError(errorMessage);
            }
        }
    };

    return html`
          <${Stack} padding=${2} spacing=${2}>
              <${Typography} variant="h4" component="div" sx=${{ flexGrow: 1, color:"#4c8c4a"}}>
                  <span style=${{color:"#009624"}}>G-Four</span>Leave
              <//>
          <//>
        <${Container} maxWidth="sm" >
            <${Box} maxWidth="sm" sx=${{
                bgcolor: '#4c8c4a',
                height: '100vh',
                display: 'flex',
                flexWrap: 'wrap',
                alignItems:'center',
                alignContent: 'center',
                justifyContent: 'center',
                }}>
                <${Paper} elevation=${3} sx=${{p: 4, minWidth: 512}}>
                    <form onSubmit=${login}>
                        <${Stack} spacing=${2}>
                            <${Typography} variant="h4" sx=${{color:"#00c853"}}>
                                Login
                            <//>
                            <${Divider}/>
                            <${TextField} label="Nama pengguna" variant="outlined" name="username" onChange=${handleChange} autoFocus required error=${usernameError !== null} helperText=${usernameError}/>
                            <${TextField} label="Kata sandi" type="password" variant="outlined" name="password" onChange=${handleChange} required error=${passwordError !== null} helperText=${passwordError}/>
                            <${Stack} direction="row" spacing=${2} justifyContent="flex-end">
                                <${Button} type="submit" variant="contained" sx=${{background:"#00c853"}}>Masuk<//>
                            <//>
                        <//>
                    </form>
                <//>
            <//>
        <//>
    `;
}
export default Login;