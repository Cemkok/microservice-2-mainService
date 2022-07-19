/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * * This class is an Entity Class.
 * @author Cem Kok
 * @Date   11 Tem 2022
 * @Time   20:01:58
 * @see
 *  * <p>  Entities in JPA are nothing but POJOs representing data that can be
 *      persisted to the database. An entity represents a table stored in a
 *      database. Every instance of an entity represents a row in the table. 
 *   The <strong> Customer </strong> class is an entity, that is, a table. </p>
 *	<table border="1">
 *      <body>
 *      <tr>
 *      <td><strong>Annotations </strong></td>
 *      <td></td>
 * 
 * 
 *      </tr>
 *      <tr>
 *      <td>@Data</td>
 *      <td>Generates getters for all fields, a useful
 *      toString method, and hashCode and equals implementations that check all
 *      non-transient fields. Will also generate setters for all non-final
 *      fields, as well as a constructor.
 *      <p>
 *      Equivalent to
 *      {@code @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode}.
 *      <p>
 *      Complete documentation is found at
 *      <a href="https://projectlombok.org/features/Data">the project lombok
 *      features page for &#64;Data</a>.</td>
 *      </tr>
 *      <tr>
 *      <td>@Entity</td>
 *      <td>Specifies that the class is an entity. This annotation is applied to
 *      the entity class. <a>@since 1.0</a>
 *      <p>
 *      Complete documentation is found at
 *      <a href="https://www.baeldung.com/jpa-entities">the project jpa-entities
 *      features page for &#64;Entity</a>.</td>
 *      <tr>
 *      <td>@Table</td>
 *      <td>Specifies the primary table for the annotated entity. Additional
 *      tables may be specified using {@link SecondaryTable} or
 *      {@link SecondaryTables} annotation.
 *      <p>
 *      If no <code>Table</code> annotation is specified for an entity class,
 *      the default values apply. Complete documentation is found at
 *      <a href="https://www.baeldung.com/jpa-entities">the project jpa-entities
 *      features page for &#64;Table</a>.</td>
 * 
 *      </tr>
 *      <tr>
 *      <td>@Column</td>
 *      <td>* Specifies the mapped column for a persistent property or field. If
 *      no <code>Column</code> annotation is specified, the default values
 *      apply. Complete documentation is found at
 *      <a href="https://www.baeldung.com/jpa-entities">the project jpa-entities
 *      features page for &#64;Column</a>.</td>
 * 
 *      </tr>
 *      <tr>
 *      <td>@Id</td>
 *      <td>Specifies the primary table for the annotated entity. Additional
 *      tables may be specified using {@link SecondaryTable} or
 *      {@link SecondaryTables} annotation.
 *      <p>
 *      If no <code>Table</code> annotation is specified for an entity class,
 *      the default values apply. Complete documentation is found at
 *      <a href="https://www.baeldung.com/jpa-entities">the project jpa-entities
 *      features page for &#64;Id</a>.</td>
 * 
 *      </tr>
 *      <tr>
 *      <td>@GeneratedValue</td>
 *      <td>Provides for the specification of generation strategies for the
 *      values of primary keys.
 *      The <code>GeneratedValue</code> annotation may be applied to a primary
 *      key property or field of an entity or mapped superclass in conjunction
 *      with the {@link Id} annotation. The use of the
 *      <code>GeneratedValue</code> annotation is only required to be supported
 *      for simple primary keys. Use of the <code>GeneratedValue</code>
 *      annotation is not supported for derived primary keys. Complete
 *      documentation is found at
 *      <a href="https://www.baeldung.com/jpa-entities">the project jpa-entities
 *      features page for &#64;GeneratedValue</a>.</td>
 * 
 *      </tr>
 *      </body>
 *      </table>
 * 
 * 
 *      <br>
 * 
 */


@Data
@Entity
@Table(name="users")

public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column (name= "username", unique=true, nullable=false, length=100)
	private String username;
	
	@Column (name= "password", nullable=false, length=100)
	private String password;
	
	@Column (name="name", nullable=false)
	private String name;
	
	@Column(name="create_time")
	private LocalDateTime createTime;
	
	
	

}
