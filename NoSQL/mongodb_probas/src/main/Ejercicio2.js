{
    "clientes"
    [
        {
            "idCliente":1,
            "nombre":"Xabier",
            "emails": [
                "xabier@cousa.com",
                "xabierIF@cousacousa.es"
            ],
            "direcciones": [
                "Calle del río 105, 1B, 15368 Dalaran",
                "Avenida del tuerto 5, 4A, 12664 Orgrimmar"
            ]
        },
        {
            "idCliente":2,
            "nombre":"Álvaro",
            "emails": [
                "alvaro@cousa.com",
                "alvaroIF@cousacousa.es"
            ],
            "direcciones": [
                "Lugar sobreForja 4, 2B, 15368 Forjaz",
                "Descenso del trueno 5, 5A, 12664 Ventormenta"
            ]
        }
    ]

}
{
    "pedidos"
    [
        {
            "idPedido":1,
            "idCliente":1,
            "fecha": new Date("2025-11-24"),
            "lineas": {
                "sku": "Nin-GBC",
                "nombre": "Nintendo GameBoyColor",
                "cantidad": 1,
                "precio": 100
            },
            "estado": "Recibido"
        },
        {
            "idPedido":2,
            "idCliente":2,
            "fecha": new Date("2026-1-20"),
            "lineas": {
                "sku": "Mag-FUN",
                "nombre": "Fundas Cartas Magic",
                "cantidad": 200,
                "precio": 0.3
            },
            "estado": "En proceso de envío"
        },
        {
            "idPedido":3,
            "idCliente":2,
            "fecha": new Date("2025-12-20"),
            "lineas": {
                "sku": "Choc-Prot",
                "nombre": "Proteínas Sabor Chocolate",
                "cantidad": 2,
                "precio": 60
            },
            "estado": "En espera de Stock"
        }
    ]
}

//Muestra los pedidos que cumplen que cantidad > 100 añadiendo un nuevo campo cantidadTotal que 
//es la suma de los campos cantidad contenidos en líneas
db.pedidos.aggregate([
    {$project: {
            _id: 0,
            idPedido: 1,
            idCliente: 1,
            fecha: 1,
            estado: 1,
            cantidadTotal: {$sum: "$lineas.cantidad"}
        }},
    {$match: {cantidadTotal: {$gt:100}}}
])

//Muestra las líneas que cumplen que la cantidad > 100
db.pedidos.aggregate([
    {$unwind: "$lineas"},
    {$match: {cantidadTotal: {$gt:100}}},
    {$project: {
            _id: 0,
            idPedido: 1,
            idCliente: 1,
            fecha: 1,
            estado: 1,
            cantidadTotal: {$sum: "$lineas.cantidad"}
        }}
])

