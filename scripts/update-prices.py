import requests
import mysql.connector

"""
Docs:
https://developers.mercadolivre.com.br/pt_br/itens-e-buscas
https://api.mercadolibre.com/sites/MLB/categories
"""
def buscar_preco_livros_mercado_livre(nome):
    resultado = {}
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
            titulo = item['title']
            preco = item['price']
            link = item['permalink']
            resultado[titulo] = {'preco': preco, 'link': link}
        else:
             print(f"NÃO ENCONTRADO: {nome}")
    except requests.exceptions.RequestException as e:
        print(f"ERRO: {nome}")
    return resultado

def get_all_books():
    query = """
    SELECT livro.titulo, livro.autor, livro.editora, marketplace_book.id FROM livro 
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
    """
    Cria um novo MarketplaceBook se livro_id não existir.
    """
    # Verifica se já existe um MarketplaceBook com o livro_id fornecido
    check_query = "SELECT id FROM MarketplaceBook WHERE livro_id = %s"
    cursor.execute(check_query, (livro_id,))
    result = cursor.fetchone()

    if not result:
        # Se não existe, cria um novo registro
        insert_query = """
        INSERT INTO MarketplaceBook (price, marketplace, url, imageUrl, title, livro_id)
        VALUES (%s, %s, %s, %s, %s, %s)
        """
        cursor.execute(insert_query, (price, marketplace, url, image_url, title, livro_id))
        db_connection.commit()
        print(f"Novo MarketplaceBook criado com livro_id {livro_id}.")
    else:
        print(f"Já existe um MarketplaceBook com livro_id {livro_id}.")


db_connection = mysql.connector.connect(
    host="localhost",
    user="root",
    password="admin",
    database="bookrecomendation"
)
cursor = db_connection.cursor()

livros = get_all_books()
for livro in livros:
    titulo = livro[0]
    autor = livro[1]
    editora = livro[2]
    marketplace_id = livro[3]

    resultado = buscar_preco_livros_mercado_livre(f"{titulo} - {autor} - {editora}")
    criar_marketplace_book(marketplace_id, resultado['preco'], 'Mercado Livre', resultado['link'], '', titulo)
    print(resultado)