package com.lepsec;

import com.lepsec.domain.impl.LRUImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactiveLrucacheApplicationTests {

	private LRUImpl lru;

	@Before
	public void setUp(){
		lru = LRUImpl.getInstance();
	}

	@Test
	public void lru_add_values_with_first_item_removed(){
		lru.put(1, "aaa");
		lru.put(2, "bbb");
		lru.put(3, "ccc");
		lru.put(4, "ddd");

		assertEquals(lru.toString(), "bbbcccddd");
	}

	@Test
	public void lru_add_values_and_use_first() throws Exception {
		lru.put(1, "aaa");
		lru.put(2, "bbb");
		lru.put(3, "ccc");
		lru.get(1);
		lru.put(4, "ddd");

		assertEquals(lru.toString(), "cccaaaddd");
	}

	@Test
	public void lru_add_values_and_use_second() throws Exception {
		lru.put(1, "aaa");
		lru.put(2, "bbb");
		lru.put(3, "ccc");
		lru.get(2);
		lru.put(4, "ddd");

		assertEquals(lru.toString(), "cccbbbddd");
	}

	@Test(expected = NullPointerException.class)
	public void lru_add_values_and_get_first() throws Exception {
		lru.put(1, "aaa");
		lru.put(2, "bbb");
		lru.put(3, "ccc");
		lru.put(4, "ddd");
		lru.get(1);
	}

}
