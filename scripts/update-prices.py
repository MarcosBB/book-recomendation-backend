import requests
import mysql.connector

"""
Docs:
https://developers.mercadolivre.com.br/pt_br/itens-e-buscas
https://api.mercadolibre.com/sites/MLB/categories
"""
def buscar_preco_livros_mercado_livre(nome):
    base_url = "https://api.mercadolibre.com/sites/MLB/search"

    try:
        response = requests.get(base_url, params={
            "q": nome, 
            "category" : "MLB1196", #"Livros, Revistas e Comics"
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

def get_all_books():
    query = """
    SELECT livro.titulo, livro.autor, livro.editora, marketplace_book.id, livro.id FROM livro 
    LEFT JOIN marketplace_book ON livro.id = marketplace_book.livro_id
    """
    cursor.execute(query)
    return cursor.fetchall()

def atualizar_marketplace_book(livro_id, price, marketplace, url, image_url, title):
    """
    Atualiza as informações de MarketplaceBook se livro_id existir.
    """
    # Verifica se existe um MarketplaceBook com o livro_id fornecido
    check_query = "SELECT id FROM MarketplaceBook WHERE livro_id = %s"
    cursor.execute(check_query, (livro_id,))
    result = cursor.fetchone()

    if result:
        # Se existe, faz o update
        update_query = """
        UPDATE MarketplaceBook 
        SET price = %s, marketplace = %s, url = %s, imageUrl = %s, title = %s
        WHERE livro_id = %s
        """
        cursor.execute(update_query, (price, marketplace, url, image_url, title, livro_id))
        db_connection.commit()
        print(f"MarketplaceBook com livro_id {livro_id} atualizado com sucesso.")
    else:
        print(f"Nenhum MarketplaceBook encontrado com livro_id {livro_id}.")

def criar_marketplace_book(livro_id, price, marketplace, url, image_url, title):
    query = """
    INSERT INTO marketplace_book (price, marketplace, url, image_url, title, livro_id)
    VALUES (%s, %s, %s, %s, %s, %s)
    """
    cursor.execute(query, (price, marketplace, url, image_url, title, livro_id))
    db_connection.commit()


db_connection = mysql.connector.connect(
    host="localhost",
    user="root",
    password="admin",
    database="bookrecomendation"
)
cursor = db_connection.cursor()

created_books = 0
updated_books = 0

livros = get_all_books()
for livro in livros:
    titulo = livro[0]
    autor = livro[1]
    editora = livro[2]
    marketplace_id = livro[3]
    id = livro[4]

    resultado = buscar_preco_livros_mercado_livre(f"{titulo} - {autor} - {editora}")
    if marketplace_id:
        # atualizar_marketplace_book(
        #     livro_id=id,
        #     price=resultado['price'],
        #     marketplace=1,
        #     url=resultado['url'],
        #     image_url=resultado['image_url'],
        #     title=resultado['title']
        # )
        updated_books += 1
    else:
        criar_marketplace_book(
            livro_id=id,
            price=resultado['price'],
            marketplace=1,
            url=resultado['url'],
            image_url=resultado['image_url'],
            title=resultado['title']
        )
        created_books += 1

print(f"{created_books} MarketplaceBooks criados.")
print(f"{updated_books} MarketplaceBooks atualizados.")