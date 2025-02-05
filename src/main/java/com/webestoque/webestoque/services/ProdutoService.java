package com.webestoque.webestoque.services;

import com.webestoque.webestoque.entities.Categoria;
import com.webestoque.webestoque.entities.Fornecedor;
import com.webestoque.webestoque.entities.Produto;
import com.webestoque.webestoque.repositories.CategoriaRepository;
import com.webestoque.webestoque.repositories.FornecedorRepository;
import com.webestoque.webestoque.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;


        public Produto createProduct(Produto produto){
            try{
                Optional<Produto> optionalProduto = produtoRepository.findByCodigo(Long.valueOf(produto.getCodigo()));
                if(optionalProduto.isPresent()) {
                    throw new RuntimeException("Já existe um produto com este código.");
                }

                Fornecedor fornecedor = fornecedorRepository.findById(produto.getFornecedor().getId())
                        .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

                Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
                        .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

                produto.setFornecedor(fornecedor);
                produto.setCategoria(categoria);
                produto.setDtCadastro(LocalDateTime.now());

                return produtoRepository.save(produto);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao salvar produto: " + e.getMessage());
            }
        }

        public List<Produto> getAllProdutos(){
            try{
                List<Produto> listProdutos = produtoRepository.findAll();
                if(!listProdutos.isEmpty()) {
                    return listProdutos;
                }else {
                    throw new RuntimeException("Nenhum produto encontrado");
                }

            }catch (Exception e){
                throw new RuntimeException("Erro ao buscar produtos" + e.getMessage());
            }
        }

        public Produto updateProdutoById(Produto produto, Long id){
            try{
                Optional<Produto> optionalProduto = produtoRepository.findById(id);

                if(optionalProduto.isEmpty()){
                   throw new RuntimeException("Produto não encontrado! id: "+ id);
                }

                Produto updateProduto = optionalProduto.get();

//                Optional<Produto> produtoPorCodigo = produtoRepository.findByCodigo(Long.valueOf(produto.getCodigo()));
//                if(produtoPorCodigo.isPresent()){
//                    throw new RuntimeException("Já existe um produto com este código. ");
//                }

                Fornecedor fornecedor = fornecedorRepository.findById(produto.getFornecedor().getId())
                        .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

                Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
                        .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));


                updateProduto.setCodigo(produto.getCodigo());
                updateProduto.setProduto(produto.getProduto());
                updateProduto.setCategoria(categoria);
                updateProduto.setFornecedor(fornecedor);
                updateProduto.setSaldo(produto.getSaldo());
                updateProduto.setPrecoCompra(produto.getPrecoCompra());
                updateProduto.setStatus(produto.getStatus());

                produtoRepository.save(updateProduto);

                return  updateProduto;
            }catch (Exception e){
                throw new RuntimeException("Erro ao atualizar produto. ");
            }
        }

        public void deleteProdutoByID(Long id){
            try {
                Optional<Produto> optionalProduto = produtoRepository.findById(id);

                if (optionalProduto.isEmpty()) {
                    throw new RuntimeException("Produto não encontrado. ID: " + id);
                }

                produtoRepository.deleteById(id);
            }catch (Exception e){
                throw new RuntimeException("Erro ao apagar produto");
            }
        }
}
