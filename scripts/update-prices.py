import requests
import mysql.connector

"""
Docs:
https://developers.mercadolivre.com.br/pt_br/itens-e-buscas
https://api.mercadolibre.com/sites/MLB/categories
"""

MARKETPLACE_ID = 1

def buscar_preco_produtos_mercado_livre(nome):
    base_url = "https://api.mercadolibre.com/sites/MLB/search"

    try:
        response = requests.get(base_url, params={
            "q": nome, 
            "category" : "MLB1144", #"Games"
            "limit": 1,
            # "sort": "price_asc",
            # "power_seller": "yes",
        })
        response.raise_for_status()  # Levanta um erro se a requisição falhar
        data = response.json()
        
        # Verifica se há itens nos resultados
        if data['results']:
            # Pega o primeiro item nos resultados
            item = data['results'][0]
            return {
                'price': item['price'],
                'url': item['permalink'],
                'image_url': item['thumbnail'],
                'title': item['title'],
            }
        else:
             print(f"NÃO ENCONTRADO: {nome}")
    except requests.exceptions.RequestException as e:
        print(f"ERRO: {nome}")
    return resultado

def get_all_products():
    query = """
    SELECT produto.titulo, marketplace_product.id, produto.id FROM produto 
    LEFT JOIN marketplace_product ON produto.id = marketplace_product.produto_id
    """
    cursor.execute(query)
    return cursor.fetchall()

def atualizar_marketplace_product(produto_id, price, marketplace_id, url, image_url, title):
    query = """
    UPDATE marketplace_product 
    SET price = %s, marketplace_id = %s, url = %s, image_url = %s, title = %s
    WHERE produto_id = %s
    """
    cursor.execute(query, (price, marketplace_id, url, image_url, title, produto_id))
    db_connection.commit()

def criar_marketplace_product(produto_id, price, marketplace_id, url, image_url, title):
    query = """
    INSERT INTO marketplace_product (price, marketplace_id, url, image_url, title, produto_id)
    VALUES (%s, %s, %s, %s, %s, %s)
    """
    cursor.execute(query, (price, marketplace_id, url, image_url, title, produto_id))
    db_connection.commit()


db_connection = mysql.connector.connect(
    host="localhost",
    user="root",
    password="admin",
    database="gameRecomendation"
)
cursor = db_connection.cursor()

created_books = 0
updated_books = 0

produtos = get_all_products()
for produto in produtos:
    titulo = produto[0]
    marketplace_product_id = produto[1]
    id = produto[2]

    resultado = buscar_preco_produtos_mercado_livre(f"{titulo}")
    if marketplace_product_id:
        atualizar_marketplace_product(
            produto_id=id,
            price=resultado['price'],
            marketplace_id=MARKETPLACE_ID,
            url=resultado['url'],
            image_url=resultado['image_url'],
            title=resultado['title']
        )
        updated_books += 1
    else:
        criar_marketplace_product(
            produto_id=id,
            price=resultado['price'],
            marketplace_id=MARKETPLACE_ID,
            url=resultado['url'],
            image_url=resultado['image_url'],
            title=resultado['title']
        )
        created_books += 1

print(f"{created_books} marketplace_product criados.")
print(f"{updated_books} marketplace_product atualizados.")