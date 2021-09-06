import html from "../js/htm.js";
import {
    Button,
    FormControl,
    MenuItem,
    TextField,
    InputLabel,
    Paper,
    Select,
    Stack,
    DialogTitle, Dialog, DialogActions, DialogContent
} from "../js/material-ui.js";
import {useState} from "../js/react.js";

function InsertCuti(props){
    const [karyawan, setKaryawan] = useState({});

    const handleChange = (event) => setKaryawan({...karyawan, [event.target.name]: event.target.value});

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(karyawan);
        fetch('/pengajuancuti', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(karyawan)
        })
            .then(response => {
                console.log(response)
                return response.json();
            })
            .then(json => console.log(json))
    };

    return html`
         <${Dialog} open=${props.open} onClose=${props.onClose} scroll="paper" component="form" fullWidth>
            <${DialogTitle}>Tambah Pengajuan Cuti<//>
            <${DialogContent} dividers>
                <${Stack}  spacing=${2}>
                    <${Paper} elevation=${3} sx=${{p: 4}}>
                        <form onSubmit=${handleSubmit}>
                            <${Stack} spacing=${2}>
                                <${TextField} id="standard-basic" label="Tanggal" variant="standard" name="tanggal" onChange=${handleChange}/>
                                <${TextField} id="standard-basic" label="Id Karyawan Pengganti" variant="standard" name="penggantiId" onChange=${handleChange}/>
                                <${TextField} id="standard-basic" label="Alamat" variant="standard" name="alamat" onChange=${handleChange}/>
                                <${TextField} id="standard-basic" label="No Telphone" variant="standard" name="noTelp" onChange=${handleChange}/>
                                <${TextField} id="standard-basic" label="Keterangan" variant="standard" name="keterangan" onChange=${handleChange}/>
                                <${TextField} id="standard-basic" label="Lama Cuti" variant="standard" name="lamaCuti" onChange=${handleChange}/>
                            <//>
                        </form>
                    <//>
                <//>
                <${DialogActions}>
                    <${Button} type="reset" variant="outlined">Kembali<//>
                    <${Button} type="submit" variant="contained">Simpan<//>
                <//>
            <//>
         <//>
    `;
}
export default InsertCuti;