/**
 * 
 */
package mz.co.geekframeworks.core.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eudson Bambo
 */
@Transactional(readOnly = true)
public abstract class AbstractQueryService extends AbstractService 
{
}
