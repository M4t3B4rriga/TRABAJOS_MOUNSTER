const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const fetch = require('node-fetch');

const app = express();
const PORT = 3000;

app.use(cors());
app.use(bodyParser.text({ type: 'text/xml' }));

app.post('/proxy', async (req, res) => {
    try {
        const response = await fetch('http://10.40.26.139:8080/ConUni_Soa_Java_ServidorGR04/ServicioConversionService', {
            method: 'POST',
            headers: {
                'Content-Type': 'text/xml',
            },
            body: req.body,
        });

        const text = await response.text();
        res.set('Content-Type', 'text/xml');
        res.send(text);
    } catch (error) {
        res.status(500).send('Error en el proxy: ' + error.message);
    }
});

app.listen(PORT, () => {
    console.log(`Proxy corriendo en http://localhost:${PORT}`);
});
