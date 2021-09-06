import html from "../js/htm.js";
import {
    Button,
    Dialog, DialogActions, DialogContent,
    DialogTitle,
    FormControl,
    InputLabel,
    MenuItem,
    Paper,
    Select,
    Stack,
    TextField
} from '../js/material-ui.js';
import {useState} from "../js/react.js";

function InsertEmployee(props) {
    const [employee, setEmployee] = useState({});

    const handleChange = (event) => setEmployee({...employee, [event.target.name]: event.target.value});

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(employee);
        fetch('/employees', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(employee)
        })
            .then(response => {
                console.log(response)
                return response.json();
            })
            .then(json => console.log(json))
    };

    return html`
        <${Dialog} open=${props.open} onClose=${props.onClose} scroll="paper" component="form" fullWidth>
            <${DialogTitle}>Tambah Employee<//>
            <${DialogContent} dividers>
                <${Stack} spacing=${2}>
                    <${TextField} id="standard-basic" label="Nama Lengkap" variant="standard" name="namaLengkap" onChange=${handleChange}/>
                    <${TextField} id="standard-basic" label="NIP" variant="standard" name="nip" onChange=${handleChange}/>
                    <${FormControl} fullWidth>
                        <${InputLabel} id="role-label" >Bagian<//>
                        <${Select}
                                labelId="role-label"
                                id="role-select"
                                value=${employee.roleId}
                                label="Bagian"
                                name="roleId"
                                onChange=${handleChange}
                        >
                            <${MenuItem} value=${1}>HRD<//>
                            <${MenuItem} value=${2}>Karyawan<//>
                           
                        <//>
                    <//>
                    <${TextField} id="standard-basic" label="Divisi" variant="standard" name="divisi" onChange=${handleChange}/>
                    <${TextField} id="standard-basic" label="Email" variant="standard" name="email" onChange=${handleChange}/>
                    <${TextField} id="standard-basic" label="Username" variant="standard" name="username" onChange=${handleChange}/>
                    <${TextField} id="standard-basic" label="Password" variant="standard" name="password" onChange=${handleChange}/>
                <//>
            <//>
            <${DialogActions}>
                <${Button} type="reset" variant="outlined">Kembali<//>
                <${Button} type="submit" variant="contained" onsubmit=${handleSubmit}>Simpan<//>
            <//>
        <//>
    `;
}

export default InsertEmployee;