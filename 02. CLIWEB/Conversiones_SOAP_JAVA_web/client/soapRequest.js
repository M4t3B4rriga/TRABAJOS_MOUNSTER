export async function enviarSOAPRequest(xml) {
    const response = await fetch('http://localhost:3000/proxy', {
        method: 'POST',
        headers: {
            'Content-Type': 'text/xml',
        },
        body: xml,
    });

    if (!response.ok) throw new Error('Error al hacer la solicitud SOAP');

    return await response.text();
}
