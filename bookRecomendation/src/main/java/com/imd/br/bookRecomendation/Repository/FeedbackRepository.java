package com.imd.br.bookRecomendation.Repository;

import com.imd.br.bookRecomendation.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
